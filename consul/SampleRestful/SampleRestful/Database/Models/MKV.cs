﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SampleRestful.Database.Models
{
    public class MKV
    {
        [Required, Key]
        public string Key { get; set; }
        public string Value { get; set; }
    }
}
