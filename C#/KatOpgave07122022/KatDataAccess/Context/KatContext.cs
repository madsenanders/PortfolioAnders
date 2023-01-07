using KatDataAccess.Model;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration.Conventions;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KatDataAccess.Context
{
    internal class KatContext : DbContext
    {
        public KatContext() : base("Katte")
        {

        }
        public DbSet<Kat> Katte { get; set; }

        public DbSet<Ejer> Ejer { get; set; }
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Conventions.Remove<PluralizingTableNameConvention>();
        }


    }
}
