package work.aki7chyan.subgroup.Utils;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

/**
 * 抽卡模拟工具类
 */
public class LotteryUtil {

    //生成随机数的基数
    private static int SourceNum = 100000000;

    public static void main(String [] args){

        int num = getTakeNum(0,0.15,10,1.5);
        System.err.println(num*1.0/1000000+"%");
//        List<Integer> list = getRollsNum(10);
        List<Integer> list = getWinNumList(num,new int[]{1,3,2,2,2},new int[]{225,1500,300,300,300,1000,2641,1117,1117});
        int all = 0;
        for (int item:list) {
            System.out.println(item*1.0/1000000+"%");
            all += item;
        }
        System.err.println(all*1.0/1000000+"%");
    }

    /**
     * 获取保底时基于基数的出货数量(暂仅处理左边界为0的情况)
     * @param left 概率左边界
     * @param right 概率右边界
     * @param times 保底次数
     * @param P 中奖期望
     * @return  int 保底时单次抽取中奖数量
     */
    public static int getTakeNum(double left, double right, int times, double P){
        if(left >= right)
            return 0;
        double middle = (left+right)/2;
        double result = pow(1 - middle, times) + times*middle - P;
        if(result >= 0.00001 || result <= -0.00001){
            if(result > 0)
                middle = getTakeNum(left,middle,times,P);
            else
                middle = getTakeNum(middle,right,times,P);
        }
        else
            return middle > 0 ?  (int)(middle*SourceNum + 0.5) : (int)(- middle*SourceNum + 0.5);
        return (int)(middle+0.5);
    }

    /**
     * 获取各级别中奖数量
     * @param winNum S+A角色概率
     * @param character 角色卡权重
     * @param source 材料权重
     * @return  List<Interger> 对应数量
     */
    public static List<Integer> getWinNumList(int winNum, int[] character, int [] source){
        List<Integer> list = new ArrayList<>();
        int weightSA = 0;
        int weightOther = 0;
        for (int item:character)
            weightSA+=item;
        for (int item:source)
            weightOther+=item;
        for (int item:character){
            list.add((int)(item*(winNum*1.0/weightSA) + 0.5));
        }
        for (int item:source){
            int a = (int)((SourceNum-winNum)*(item*1.0/weightOther)+0.5);
            list.add(a);
        }
        return list;
    }

    /**
     * 获取生成随机数
     * @param times 生成随机数个数
     * @return  List<Integer> 随机数列
     */
    public static List<Integer> getRollsNum(int times){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < times; i++){
            int a = (int) (Math.random()* SourceNum);
            list.add(a);
        }
        return list;
    }
}
