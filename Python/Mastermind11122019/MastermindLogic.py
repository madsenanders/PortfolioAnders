import random

class Code():
	def __init__(self):
		self.colors = ['R','G','B','Y','P','M']
		self.code = []

	def get_code(self):
		return self.code

	def	set_code(self,input):
		self.code.append(input)
	
	def get_joined_code(self):
		return "".join(self.code)


class Solution(Code):
	def set_random_solution(self):
		for i in range(4):
			color = self.colors[random.randint(0,5)]
			self.set_code(color)

	def set_user_solution(self):
		code = (input("Indtast kode:"))
		for i in code:
			if i in self.colors and len(code)==4:
				self.set_code(i)
			else:
				self.code.clear()
				print("Type real colors")
				self.set_user_solution()
				break

	def get_game_type(self):
		game_type = input("Vil du selv sætte koden=[y/n]")

		if game_type == "y":
			self.set_user_solution()

		elif game_type == "n":
			self.set_random_solution()

		else:
			print("Illigal input")
			self.get_game_type()



class Guess(Code):
	def make_guess(self):
		guess = input("Skriv nyt gæt:")
		
		while len(guess) == 0:
			guess = input("Skriv nyt gæt:")

		self.code.clear()
		for i in guess:
			if i in self.colors and len(guess)== 4:
				self.set_code(i)
			else:
				self.code.clear()
				print("Type real colors")
				self.make_guess()
				break


class Feedback(Code):
	def match(self, original_guess, original_solution):
		guess = original_guess.copy()
		solution = original_solution.copy()
		self.code.clear()
		
		for i in range(4):
			if solution[i] == guess[i]:
				self.set_code('B')
				guess[i] = 'Brugt gæt'
				solution[i] = 'Brugt løsning'

		for i in range(4):
			j = 0
			while j < 4:
				if solution[i] == guess[j] and j != i:
					self.set_code('W')
					guess[j] = 'Brugt gæt'
					break
				j += 1

		if len(self.get_code()) < 4:
			while len(self.get_code()) < 4:
				self.set_code('_')		

		self.code.sort()


def play():
	s = Solution()
	g = Guess()
	f = Feedback()

	game_state = 0
	game_type = ""

	print("Velkommen til Mastermind!! \nDu kan vælge mellem følgende farver: \n \nR  G  B  Y  P  M \n")
	
	s.get_game_type()

	print("Spil Startet \n")


	for i in range(10):
		print(f"Antal gæt tilbage: {10 - i}")
		g.make_guess()
		f.match(g.get_code(),s.get_code())
		print(f.get_joined_code())


		if f.get_joined_code() == "BBBB":
			game_state = 1
			break

		else:
			game_state = 2

	if game_state == 1:
		print("Vinder!!!")

	else:
		print("Du gættede desværre ikke koden... Koden var: " + s.get_joined_code())


play()
