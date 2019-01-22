using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SampleRestful.Database.Models
{
    public class MUser
    {
        [Required, Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int ID { get; set; }
        public string LoginID { get; set; }
        public string Password { get; set; }
        public DateTime RegistDate { get; set; }


    }
}
