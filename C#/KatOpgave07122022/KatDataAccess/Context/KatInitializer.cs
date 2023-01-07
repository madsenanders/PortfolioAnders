using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using KatDataAccess.Model;

namespace KatDataAccess.Context
{
    internal class KatInitializer : CreateDatabaseIfNotExists<KatContext>
    {
        protected override void Seed(KatContext context)
        {
            Ejer e1 = new Ejer(1, "Anders");
            Ejer e2 = new Ejer(2, "Erik");

            Kat k1 = new Kat(1, "Dypon", "Sort", true, e1);
            Kat k2 = new Kat(2, "Luna", "BrunHvid", true, e1);
            Kat k3 = new Kat(3, "Harry", "BrunGrå", false, e1);
            Kat k4 = new Kat(4, "Sofus", "SortHvid", false, e2);

            e1.Katte.Add(k1);
            e1.Katte.Add(k2);
            e1.Katte.Add(k3);
            e2.Katte.Add(k4);
            
            context.Ejer.Add(e1);
            context.Ejer.Add(e2);
            context.Katte.Add(k1);
            context.Katte.Add(k2);
            context.Katte.Add(k3);
            context.Katte.Add(k4);

            context.SaveChanges();

        }
        private void dummy()
        {
            string result = System.Data.Entity.SqlServer.SqlFunctions.Char(65);
        }
    }
}
