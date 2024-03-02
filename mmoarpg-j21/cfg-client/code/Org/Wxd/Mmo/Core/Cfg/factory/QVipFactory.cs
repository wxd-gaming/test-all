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
    /// excel 构建 Vip.xlsx - q_vip - vip等级
    /// <summary>
    public class QVipFactory
    {
        public QVipFactory()
        {
            string jsonString = File.ReadAllText("config_json/q_vip.json");
            Beans = Newtonsoft.Json.JsonConvert.DeserializeObject<List<QVipBean>>(jsonString);
            foreach (var bean in Beans)
            {
                beanMap[bean.QId] = bean;
            }
        }

        public List<QVipBean> Beans { get; set; }
        
        private Dictionary<int, QVipBean> beanMap = new Dictionary<int, QVipBean>();

        public Dictionary<int, QVipBean> BeanMap
        {
            get { return beanMap; }
            set { beanMap = value; }
        }

    }
}