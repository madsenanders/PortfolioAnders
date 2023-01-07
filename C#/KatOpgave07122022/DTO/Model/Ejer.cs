using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DTO.Model
{
    public class Ejer
    {
        public Ejer(int EjerId, string navn)
        {
            EjerId = EjerId;
            Navn = navn;
        }

        public Ejer(int EjerId, string navn, List<Kat> katte)
        {
            EjerId = EjerId;
            Navn = navn;
            katte = katte;
        }

        public int EjerId { get; set; }
        [Required]
        public string Navn { get; set; }
        [Range(0, 50)]
        public int Alder { get; set; }

        public List<Kat> Katte { get; set; } = new List<Kat>();

    }
}
