package term1.yatzy.gui;

import java.util.Arrays;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import term1.yatzy.model.Yatzy;

public class MainApp extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Yatzy");
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	private TextField[] txfValues;
	private CheckBox[] chbHolds;
	private TextField[] txfResults;
	private TextField txfSumSame, txfBonus, txfSumOther, txfTotal;
	private Label lblRolled;
	private Button btnRoll;
	private boolean hasConfirmedAlready = false;
	private Yatzy logicYatzy = new Yatzy();

	private void initContent(GridPane pane) {
		pane.setGridLinesVisible(false);
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);

		dicePane(pane);
		scorePane(pane);
	}

	private void dicePane(GridPane pane) {
		GridPane dicePane = new GridPane();
		pane.add(dicePane, 0, 0);
		dicePane.setGridLinesVisible(false);
		dicePane.setPadding(new Insets(10));
		dicePane.setHgap(10);
		dicePane.setVgap(10);
		dicePane.setStyle("-fx-border-color: black");

		this.txfValues = new TextField[5];
		this.chbHolds = new CheckBox[5];
		
		diceFields(dicePane);
		rollComponents(dicePane);

	}

	private void rollComponents(GridPane dicePane) {
		btnRoll = new Button("Roll");
		btnRoll.setOnAction(event -> this.rollDices());
		dicePane.add(this.btnRoll, 3, 2);

		this.lblRolled = new Label("Rolls: 0/3");
		dicePane.add(this.lblRolled, 4, 2);
	}

	private void diceFields(GridPane dicePane) {
		for (int i = 0; i < this.txfValues.length; i++) {
			txfValues[i] = new TextField("0");
			txfValues[i].setDisable(true);
			txfValues[i].setPrefSize(100, 100);
			txfValues[i].setFont(Font.font(50));
			txfValues[i].setAlignment(Pos.CENTER);
			dicePane.add(txfValues[i], i, 0);

			CheckBox checkBox = new CheckBox();
			this.chbHolds[i] = checkBox;
			dicePane.add(checkBox, i, 1);
		}
	}

	private void rollDices() {
		if (this.hasConfirmedAlready) {
			this.hasConfirmedAlready = false;
		}
		if (this.logicYatzy.getThrowCount() < 3) {
			boolean[] holds = new boolean[5];
			for (int i = 0; i < this.chbHolds.length; i++) {
				holds[i] = this.chbHolds[i].isSelected();
			}
			logicYatzy.throwDice(holds);
			int[] newValues = logicYatzy.getValues();
			this.lblRolled.setText("Rolls: " + logicYatzy.getThrowCount() + "/3");

			this.updateFaceValuesOnGui(newValues);
			this.calcScoring();
		}
	}

	private void updateFaceValuesOnGui(int[] newValues) {
		for (int i = 0; i < newValues.length; i++) {
			this.txfValues[i].setText(Integer.toString(newValues[i]));
		}
	}

	private void scorePane(GridPane pane) {
		GridPane scorePane = new GridPane();
		pane.add(scorePane, 0, 1);
		scorePane.setGridLinesVisible(false);
		scorePane.setPadding(new Insets(10));
		scorePane.setVgap(5);
		scorePane.setHgap(10);
		scorePane.setStyle("-fx-border-color: black");
		int w = 50; 

		initializeTxfResults(scorePane, w);
		sumFields(scorePane);

	}

	private void initializeTxfResults(GridPane scorePane, int w) {
		this.txfResults = new TextField[15];
		for (int i = 0; i < txfResults.length; i++) {
			this.txfResults[i] = new TextField("0");
			this.txfResults[i].setPrefWidth(w);
			this.txfResults[i].setOnMouseClicked(event -> handleScoreConfirm(event));
			scorePane.add(this.txfResults[i], 1, i);

			Label lbl = new Label(determineLabel(i));
			scorePane.add(lbl, 0, i);
		}
	}

	private void sumFields(GridPane scorePane) {
		this.txfSumSame = new TextField("0");
		this.txfSumSame.setDisable(true);
		this.txfSumSame.setPrefWidth(50);
		;

		this.txfSumOther = new TextField("0");
		this.txfSumOther.setDisable(true);
		this.txfSumOther.setPrefWidth(50);
		;

		this.txfBonus = new TextField("0");
		this.txfBonus.setDisable(true);
		this.txfBonus.setPrefWidth(50);
		;

		this.txfTotal = new TextField("0");
		this.txfTotal.setDisable(true);
		this.txfTotal.setPrefWidth(50);
		;

		Label lblSum1 = new Label("Sum: ");
		Label lblSum2 = new Label("Sum: ");
		Label lblBonus = new Label("Bonus: ");
		Label lblTotal = new Label("Total: ");

		scorePane.add(txfSumSame, 3, 5);
		scorePane.add(txfSumOther, 3, 14);
		scorePane.add(txfBonus, 5, 5);
		scorePane.add(txfTotal, 5, 14);
		scorePane.add(lblSum1, 2, 5);
		scorePane.add(lblSum2, 2, 14);
		scorePane.add(lblBonus, 4, 5);
		scorePane.add(lblTotal, 4, 14);
	}

	private void calcScoring() {
		for (int i = 0; i < this.txfResults.length; i++) {
			this.determineScoringAlgorithm(i);
		}
	}

	private void determineScoringAlgorithm(int i) {
		if (!checkIfFieldDisabled(i)) {
			if (i >= 0 && i <= 5) {
				this.updateTextFieldValue(i, logicYatzy.sameValuePoints(i + 1));
			} else if (i == 6) {
				this.updateTextFieldValue(i, logicYatzy.onePairPoints());
			} else if (i == 7) {
				this.updateTextFieldValue(i, logicYatzy.twoPairPoints());
			} else if (i == 8) {
				this.updateTextFieldValue(i, logicYatzy.threeSamePoints());
			} else if (i == 9) {
				this.updateTextFieldValue(i, logicYatzy.fourSamePoints());
			} else if (i == 10) {
				this.updateTextFieldValue(i, logicYatzy.fullHousePoints());
			} else if (i == 11) {
				this.updateTextFieldValue(i, logicYatzy.smallStraightPoints());
			} else if (i == 12) {
				this.updateTextFieldValue(i, logicYatzy.largeStraightPoints());
			} else if (i == 13) {
				this.updateTextFieldValue(i, logicYatzy.chancePoints());
			} else {
				this.updateTextFieldValue(i, logicYatzy.yatzyPoints());
			}
		}
	}

	private boolean checkIfFieldDisabled(int i) {
		return this.txfResults[i].isDisabled();
	}

	private void updateTextFieldValue(int i, int updateValue) {
		this.txfResults[i].setText(Integer.toString(updateValue));
	}

	private void handleScoreConfirm(Event event) {
		if (!this.hasConfirmedAlready) {
			TextField txt = (TextField) event.getSource();
			txt.setDisable(true);
			this.calculateSumTotalBonus(txt);
			logicYatzy.resetThrowCount();
			logicYatzy.resetFaceValues();
			this.updateFaceValuesOnGui(logicYatzy.getValues());
			this.lblRolled.setText("Rolls: 0/3");
			this.calcScoring();
			this.hasConfirmedAlready = !this.hasConfirmedAlready;

			for (int i = 0; i < this.chbHolds.length; i++) {
				this.chbHolds[i].setSelected(false);
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.headerTextProperty().setValue("You have already chosen a score this round");
			alert.show();
		}
	}

	private void calculateSumTotalBonus(TextField txt) {
		int indexOfEvent = Arrays.asList(this.txfResults).indexOf(txt);
		int eventFieldValue = Integer.parseInt(txt.getText());

		if (indexOfEvent >= 0 && indexOfEvent <= 5) {
			this.txfSumSame.setText(Integer.toString(Integer.parseInt(this.txfSumSame.getText()) + eventFieldValue));
			if (Integer.parseInt(this.txfSumSame.getText()) > 63) {
				this.txfBonus.setText("50");
			}
		} else {
			this.txfSumOther.setText(Integer.toString(Integer.parseInt(this.txfSumOther.getText()) + eventFieldValue));
		}
		this.txfTotal.setText(Integer.toString(Integer.parseInt(this.txfSumSame.getText())
				+ Integer.parseInt(this.txfBonus.getText()) + Integer.parseInt(this.txfSumOther.getText())));
	}

	private String determineLabel(int i) {
		if (i >= 0 && i <= 5) {
			return (i + 1) + "-s";
		} else if (i == 6) {
			return "One Pair";
		} else if (i == 7) {
			return "Two Pairs";
		} else if (i == 8) {
			return "Three Same";
		} else if (i == 9) {
			return "Four Same";
		} else if (i == 10) {
			return "Full House";
		} else if (i == 11) {
			return "Small Straight";
		} else if (i == 12) {
			return "Large Straight";
		} else if (i == 13) {
			return "Chance";
		} else {
			return "Yatzy";
		}
	}

}
