using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KatDataAccess.Model
{
    internal class Ejer
    {
        public Ejer()
        {
        }

        public Ejer(string navn, List<Kat> katte)
        {
            Navn = navn;
            katte = katte;
        }

        public Ejer(int ejerId, string navn, List<Kat> katte)
        {
            EjerId = ejerId;
            Navn = navn;
            katte = katte;
        }

        public Ejer(int ejerId, string navn)
        {
            EjerId = ejerId;
            Navn = navn;
        }

        

        public int EjerId { get; set; }
        public string Navn { get; set; }

        public List<Kat> Katte { get; set; } = new List<Kat>();

        
    }

}

