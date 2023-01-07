using KatDataAccess.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KatDataAccess.Mappers
{
    internal class KatteMapper
    {
        public static DTO.Model.Kat Map(Kat kat)
        {
            return new DTO.Model.Kat(kat.KatId, kat.Navn, kat.Pelsfarve, kat.Levende, simpleMap(kat.Ejer));
        }
        public static Kat Map(DTO.Model.Kat kat)
        {
            // går galt her, det ender med at lave en kopi af den tilsendte ejer
            // sker sandsynligvis fordi den ejer ikke har en katteliste og så er den unik
            // men hele pointed med simplemap er at den ikke har en katteliste for at undgå infinite loop
            // så ved ikke hvordan jeg fikser det uden at ødelægge associationen
            return new Kat(kat.KatId, kat.Navn, kat.Pelsfarve, kat.Levende, simpleMap(kat.Ejer));
        }

        public static DTO.Model.Kat simpleMap(Kat kat)
        {
            return new DTO.Model.Kat(kat.KatId, kat.Navn, kat.Pelsfarve, kat.Levende);
        }

        public static Kat simpleMap(DTO.Model.Kat kat)
        {
            return new Kat(kat.KatId, kat.Navn, kat.Pelsfarve, kat.Levende);
        }


        public static List<DTO.Model.Kat> Map (List<Kat> katte)
        {

            List<DTO.Model.Kat> result = new List<DTO.Model.Kat>();
            foreach (Kat kat in katte)
            {
                result.Add(Map(kat));
                
            }
            return result;
        }

        internal static void Update(DTO.Model.Kat kat, Kat dataemp)
        {
            dataemp.Navn = kat.Navn;
            dataemp.Pelsfarve = kat.Pelsfarve;
            dataemp.Levende = kat.Levende;
            
            
        }


        public static DTO.Model.Ejer Map(Ejer ejer)
        {
            List<DTO.Model.Kat> katteliste = new List<DTO.Model.Kat>();


            foreach (Kat kat in ejer.Katte){
                katteliste.Add(simpleMap(kat));
            }

            return new DTO.Model.Ejer(ejer.EjerId, ejer.Navn, katteliste);
        }
        public static Ejer Map(DTO.Model.Ejer ejer)
        {
            List<Kat> katteliste = new List<Kat>();

            foreach (DTO.Model.Kat kat in ejer.Katte)
            {
                katteliste.Add(simpleMap(kat));
            }

            return new Ejer(ejer.EjerId, ejer.Navn, katteliste);
        }

        public static DTO.Model.Ejer simpleMap(Ejer ejer)
        {
            return new DTO.Model.Ejer(ejer.EjerId, ejer.Navn);
        }

        public static Ejer simpleMap(DTO.Model.Ejer ejer)
        {

            return new Ejer(ejer.EjerId, ejer.Navn);
        }
    }
}
