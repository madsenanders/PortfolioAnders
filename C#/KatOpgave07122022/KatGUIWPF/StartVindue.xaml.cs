using BusinessLogic;
using DTO.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace KatGUIWPF
{
    /// <summary>
    /// Interaction logic for Window1.xaml
    /// </summary>
    public partial class Window1 : Window
    {
        public Window1()
        {
            InitializeComponent();
        }

        KatBLL bll = new KatBLL();
        private void SearchButton_Click(object sender, RoutedEventArgs e)
        {
            Kat kat = bll.getKat(Int32.Parse(SearchId.Text));
            Navn.Content = kat.Navn;
            Pelsfarven.Content = kat.Pelsfarve;
            EjerFeltet.Content = kat.Ejer.Navn;
            if (kat.Levende == true)
            {
                Lever.Content = "Lever";
            } else
            {
                Lever.Content = "Lever Ikke";
            }
        }

        private void Add_Click(object sender, RoutedEventArgs e)
        {

            Kat kat = new Kat(7, NameToAdd.Text, Pelsfarve.Text, (bool)Levende.IsChecked, bll.getEjer(Int32.Parse(EjerIdFelt.Text)));

            bll.AddKat(kat);
        }

        private void VisKatte_Click(object sender, RoutedEventArgs e)
        {
            KatListe.Items.Clear();

            List<DTO.Model.Kat> kattene = bll.AddAlleKatte();

            foreach (Kat kat in kattene)
            {
                KatListe.Items.Add(kat.Navn);

            }
            

        }

        private void VisKatteCheckbox_Click(object sender, RoutedEventArgs e)
        {
            KatListe.Items.Clear();
            List<DTO.Model.Kat> kattene;
            if ((bool)LevendeKatte.IsChecked)
            {
                kattene = bll.AddAlleLevendeKatte();
            } else
            {
                kattene = bll.AddAlleIkkeLevendeKatte();
            }

            foreach (Kat kat in kattene)
            {
                KatListe.Items.Add(kat.Navn);

            }


        }

        private void RedigerKatte_Click(object sender, RoutedEventArgs e)
        {
            Kat kat = bll.getKat(Int32.Parse(RedigerId.Text));
            kat.Navn = RedigerNavn.Text;
            kat.Pelsfarve = RedigerPelsfarve.Text;
            kat.Levende = (bool)RedigerLevende.IsChecked;
            

            bll.EditKat(kat);
        }
    }
}
