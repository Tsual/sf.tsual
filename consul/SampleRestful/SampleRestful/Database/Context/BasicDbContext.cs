using Microsoft.EntityFrameworkCore;
using SampleRestful.Database.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SampleRestful.Database.Context
{
    public class BasicDbContext :DbContext
    {
        public DbSet<MUser> SUser { get; set; }
        public DbSet<MKV> SKV { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            //optionsBuilder.UseSqlite("Data Source = default.db;");
            optionsBuilder.UseMySQL("Server=20.26.38.75;Database=db0;Uid=dbu;Pwd=123456;");
        }
    }
}
