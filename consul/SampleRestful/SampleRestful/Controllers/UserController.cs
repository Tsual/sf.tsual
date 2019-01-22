using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using SampleRestful.Database.Context;
using SampleRestful.Database.Models;

namespace SampleRestful.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        BasicDbContext _context;
        public UserController(BasicDbContext _context)
        {
            this._context = _context;
            _context.Database.EnsureCreated();
        }

        [HttpPost]
        public async Task<IActionResult> Regist([FromBody] MUser user)
        {
            if (!ModelState.IsValid) return BadRequest();

            if (_context.SUser.Any(u => u.ID == user.ID)) return Forbid();
            user.RegistDate = DateTime.Now;
            _context.SUser.Add(user);
            await _context.SaveChangesAsync();

            return Ok();
        }

        [HttpPost]
        public async Task<IActionResult> Verify([FromBody] MUser mUser)
        {
            if (!ModelState.IsValid) return BadRequest();
            var qUser = await _context.SUser.FindAsync(null, mUser.LoginID);
            if (qUser != null)
                if (qUser.Password == mUser.Password) return Ok();
            return Forbid();
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetUser([FromRoute] int id)
        {
            if (!ModelState.IsValid) return BadRequest();
            var user = await _context.SUser.FindAsync(id);

            if (user == null)
            {
                return NotFound();
            }

            return Ok(user);
        }
    }
}