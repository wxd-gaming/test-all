using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Org.Wxd.Mmo.Core.Cfg.Bean;

namespace Org.Wxd.Mmo.Core.Cfg.Table
{

    /// <summary>
    /// excel 构建 Vip.xlsx - q_itemshop_vip - vip礼包
    /// <summary>
    public class QItemshopVipTable
    {
        public QItemshopVipTable()
        {
            string jsonString = File.ReadAllText("config_json/q_itemshop_vip.json");
            Rows = Newtonsoft.Json.JsonConvert.DeserializeObject<List<QItemshopVipRow>>(jsonString);
            foreach (var row in Rows)
            {
                rowMap[bean.QId] = row;
            }
        }

        public List<QItemshopVipRow> Rows { get; set; }
        
        private Dictionary<int, QItemshopVipRow> rowMap = new Dictionary<int, QItemshopVipRow>();

        public Dictionary<int, QItemshopVipRow> RowMap
        {
            get { return rowMap; }
            set { rowMap = value; }
        }

    }
}