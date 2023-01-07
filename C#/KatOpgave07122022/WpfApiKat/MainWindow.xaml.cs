using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using BusinessLogic;
using DTO.Model;

namespace WpfApiKat
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }



        private void SearchButton_Click(object sender, RoutedEventArgs e)
        {
                KatBLL bll = new KatBLL();
                string id = SearchId.Text;
                HttpClient client = new HttpClient();

                client.DefaultRequestHeaders.Accept.Clear();
                // virker ikke af en grund uden for min forståelse
                Task<String> task = client.GetStringAsync("Https://localhost:44324/api/Kat/" + id);
                var result = task.Result;
                Console.WriteLine(result);
                Kat kat = JsonSerializer.Deserialize<Kat>(result);
                
                Navn.Content = kat.Navn;
                Pelsfarven.Content = kat.Pelsfarve;
                
                if (kat.Levende == true)
                {
                    Lever.Content = "Lever";
                }
                else
                {
                    Lever.Content = "Lever Ikke";
                
            }
        }
    }
}
