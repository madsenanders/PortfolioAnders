using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace WebApi.Controllers
{
    public class KatController : Controller
    {
        // GET: Kat
        public ActionResult Index()
        {
            return View();
        }
    }
}