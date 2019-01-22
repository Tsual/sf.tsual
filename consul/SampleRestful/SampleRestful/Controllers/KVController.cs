using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SampleRestful.Database.Context;
using SampleRestful.Database.Models;

namespace SampleRestful.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class KVController : ControllerBase
    {
        private readonly BasicDbContext _context;

        public KVController(BasicDbContext context)
        {
            _context = context;
            _context.Database.EnsureCreated();
        }

        // GET: api/KV
        [HttpGet]
        public async Task<ActionResult<IEnumerable<MKV>>> GetSKV()
        {
            return await _context.SKV.ToListAsync();
        }

        // GET: api/KV/5
        [HttpGet("{id}")]
        public async Task<ActionResult<MKV>> GetMKV(string id)
        {
            var mKV = await _context.SKV.FindAsync(id);

            if (mKV == null)
            {
                return NotFound();
            }

            return mKV;
        }

        // PUT: api/KV/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutMKV(string id, MKV mKV)
        {
            if (id != mKV.Key)
            {
                return BadRequest();
            }

            _context.Entry(mKV).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!MKVExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/KV
        [HttpPost]
        public async Task<ActionResult<MKV>> PostMKV(MKV mKV)
        {
            _context.SKV.Add(mKV);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetMKV", new { id = mKV.Key }, mKV);
        }

        // DELETE: api/KV/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<MKV>> DeleteMKV(string id)
        {
            var mKV = await _context.SKV.FindAsync(id);
            if (mKV == null)
            {
                return NotFound();
            }

            _context.SKV.Remove(mKV);
            await _context.SaveChangesAsync();

            return mKV;
        }

        private bool MKVExists(string id)
        {
            return _context.SKV.Any(e => e.Key == id);
        }
    }
}
