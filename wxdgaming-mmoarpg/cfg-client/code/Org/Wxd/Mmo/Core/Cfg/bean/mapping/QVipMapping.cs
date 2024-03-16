using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace wxdgaming.mmo.Core.Cfg.Bean.Mapping
{

    /// <summary>
    /// excel 构建 Vip.xlsx - q_vip - vip等级
    /// </summary>
    public class QVipMapping
    {
        /// <summary>
        /// vip等级
        /// </summary>
        public int Id { get; set; }
        /// <summary>
        /// vip升级所需要的经验
        /// </summary>
        public int Exp { get; set; }
        /// <summary>
        /// vip名字
        /// </summary>
        public String VipName { get; set; }
        /// <summary>
        /// 解锁后获得奖励
        /// </summary>
        public int[][] Rewards { get; set; }
    }
}