using DTO.Model;
using KatDataAccess.Context;
using KatDataAccess.Mappers;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Runtime.Remoting.Contexts;
using System.Text;
using System.Threading.Tasks;



namespace KatDataAccess.Repositories
{
    public class KatRepository
    {
        public static Kat getKat(int id)
        {
            using (KatContext context = new KatContext())
            {
                return KatteMapper.Map(context.Katte.Where(kat => kat.KatId == id).Include(kat => kat.Ejer).FirstOrDefault());
            }
        }
        public static void AddKat(Kat kat)
        {
            using (KatContext context = new KatContext()) 
            {



                context.Katte.Add(KatteMapper.Map(kat));
                context.SaveChanges();
            }
        }

        public static List<DTO.Model.Kat> GetAlleKatte()
        {
            using (KatContext context = new KatContext())
            {

                return KatteMapper.Map(context.Katte.Include(kat => kat.Ejer).ToList());
                
                
            }
        }

        public static List<DTO.Model.Kat> GetAlleLevendeKatte()
        {
            using (KatContext context = new KatContext())
            {
                return KatteMapper.Map(context.Katte.Include(kat => kat.Ejer).Where(k => k.Levende == true).ToList());
                //return KatteMapper.Map(context.Katte.Where(k => k.Levende == true).ToList());


            }
        }

        public static List<DTO.Model.Kat> GetAlleIkkeLevendeKatte()
        {
            using (KatContext context = new KatContext())
            {

                return KatteMapper.Map(context.Katte.Include(kat => kat.Ejer).Where(k => k.Levende == false).ToList());


            }
        }

        public static void EditKat(Kat kat)
        {
            using (KatContext context = new KatContext())
            {

            Model.Kat dataemp = context.Katte.Find(kat.KatId);
            KatteMapper.Update(kat, dataemp);
            context.SaveChanges();
            }
        }

    public static Ejer getEjer(int id)
        {
            using(KatContext context = new KatContext())
            {
                return KatteMapper.Map(context.Ejer.Where(ejer => ejer.EjerId == id).Include(ejer => ejer.Katte).FirstOrDefault());
            }
        }
        
        public static void AddEjer(Ejer ejer)
        { using(KatContext context = new KatContext())
            {
                context.Ejer.Add(KatteMapper.Map(ejer));
                context.SaveChanges();
            }
        
        }
    }
}
