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
    /// excel 构建 Vip.xlsx - q_vip - vip等级
    /// <summary>
    public class QVipTable
    {
        public QVipTable()
        {
            string jsonString = File.ReadAllText("config_json/q_vip.json");
            Rows = Newtonsoft.Json.JsonConvert.DeserializeObject<List<QVipRow>>(jsonString);
            foreach (var row in Rows)
            {
                rowMap[bean.QId] = row;
            }
        }

        public List<QVipRow> Rows { get; set; }
        
        private Dictionary<int, QVipRow> rowMap = new Dictionary<int, QVipRow>();

        public Dictionary<int, QVipRow> RowMap
        {
            get { return rowMap; }
            set { rowMap = value; }
        }

    }
}