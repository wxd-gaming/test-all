using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Org.Wxd.Mmo.Core.Cfg.Bean.Mapping
{

    /// <summary>
    /// excel 构建 Vip礼包.xlsx - q_itemshop_vip - vip礼包
    /// </summary>
    public class QItemshopVipMapping
    {
        /// <summary>
        /// 主键id
        /// </summary>
        public int Id { get; set; }
        /// <summary>
        /// 商品内容
        /// </summary>
        public int[][] ShopItem { get; set; }
        /// <summary>
        /// 如果数据库关联字符串超长
        /// </summary>
        public String Name1 { get; set; }
        /// <summary>
        /// 客户端使用字段
        /// </summary>
        public String GiftName { get; set; }
        /// <summary>
        /// 是非类型
        /// </summary>
        public boolean ConditionsViplv { get; set; }
        /// <summary>
        /// 小数类型
        /// </summary>
        public float LimitNum { get; set; }
        /// <summary>
        /// 售价
        /// </summary>
        public int[] Price { get; set; }
    }
}