using DTO.Model;
using KatDataAccess.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BusinessLogic
{
    public class KatBLL
    {
        public Kat getKat(int id)
        {

            if (id < 0) throw new IndexOutOfRangeException();
            return KatRepository.getKat(id);
        }
        public void AddKat(Kat kat)
        {
            
            KatRepository.AddKat(kat);
        }

        public List<DTO.Model.Kat> AddAlleKatte()
        {
            return KatRepository.GetAlleKatte();
        }

        public List<DTO.Model.Kat> AddAlleLevendeKatte()
        {
            return KatRepository.GetAlleLevendeKatte();
        }

        public List<DTO.Model.Kat> AddAlleIkkeLevendeKatte()
        {
            return KatRepository.GetAlleIkkeLevendeKatte();
        }

        public void EditKat(Kat kat)
        {
            KatRepository.EditKat(kat);
        }

        
        public Ejer getEjer(int id)
        {

            if (id < 0) throw new IndexOutOfRangeException();
            return KatRepository.getEjer(id);
        }

        

        public void AddEjer(Ejer ejer)
        {

            KatRepository.AddEjer(ejer);
        }

        

        public void EditEjer (Ejer ejer)
        {

        }

    }
}
