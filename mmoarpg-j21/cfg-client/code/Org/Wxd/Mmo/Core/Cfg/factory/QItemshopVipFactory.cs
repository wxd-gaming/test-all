using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Org.Wxd.Mmo.Core.Cfg.Bean;

namespace Org.Wxd.Mmo.Core.Cfg.Factory
{

    /// <summary>
    /// excel 构建 Vip礼包.xlsx - q_itemshop_vip - vip礼包
    /// <summary>
    public class QItemshopVipFactory
    {
        public QItemshopVipFactory()
        {
            string jsonString = File.ReadAllText("config_json/q_itemshop_vip.json");
            Beans = Newtonsoft.Json.JsonConvert.DeserializeObject<List<QItemshopVipBean>>(jsonString);
            foreach (var bean in Beans)
            {
                beanMap[bean.QId] = bean;
            }
        }

        public List<QItemshopVipBean> Beans { get; set; }
        
        private Dictionary<int, QItemshopVipBean> beanMap = new Dictionary<int, QItemshopVipBean>();

        public Dictionary<int, QItemshopVipBean> BeanMap
        {
            get { return beanMap; }
            set { beanMap = value; }
        }

    }
}