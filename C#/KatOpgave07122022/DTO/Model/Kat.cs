using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DTO.Model
{
    public class Kat
    {
        public Kat(int katId, string navn, string pelsfarve, bool levende, Ejer ejer)
        {
            KatId = katId;
            Navn = navn;
            Pelsfarve = pelsfarve;
            Levende = levende;
            Ejer = ejer;

        }

        public Kat(int katId, string navn, string pelsfarve, bool levende)
        {
            KatId = katId;
            Navn = navn;
            Pelsfarve = pelsfarve;
            Levende = levende;

        }

        public virtual int KatId { get; set; }
        [Required]
        public virtual string Navn { get; set; }
        [Range(0, 50)]
        public virtual string Pelsfarve { get; set; }
        public virtual bool Levende { get; set; }
        public virtual Ejer Ejer { get; set; }
    }
}
