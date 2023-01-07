using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KatDataAccess.Model
{
    internal class Kat
    {
        public Kat()
        {
        }


        public Kat(string navn, string pelsfarve, bool levende, Ejer ejer)
        {

            Navn = navn;
            Pelsfarve = pelsfarve;
            Levende = levende;
            ejer = ejer;
        }

        public Kat(int katId, string navn, string pelsfarve, bool levende, Ejer ejer)
        {
            KatId = katId;
            Navn = navn;
            Pelsfarve = pelsfarve;
            Levende = levende;
            Ejer = ejer;
        }

        public Kat(string navn)
        {
            Navn = navn;
        }

        public Kat(int katId, string navn, string pelsfarve, bool levende)
        {
            KatId = katId;
            Navn = navn;
            Pelsfarve = pelsfarve;
            Levende = levende;
        }

        public virtual int KatId { get; set; }
        public virtual string Navn { get; set; }
        public virtual string Pelsfarve { get; set; }
        public virtual bool Levende { get; set; }

        public virtual Ejer Ejer { get; set; }
    }
}
