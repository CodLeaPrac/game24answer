package calclatour;


import java.util.ArrayList;
import java.util.List;

public class Game24 {


    private Calculator calculator;

    public Game24(Calculator calculator){
        this.calculator = calculator;
    }


    /**
     * 根据24点 4个数字 获取所有可能的组合 每种组合放入List中
     * @param a 存放4个数字
     * @return
     * eg: [3,4,5,6]
     */
    private  ArrayList<int[]> combine4Num(int[] a) {
        ArrayList<int[]> arrayList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {             		//表示第一个下标的排列位置
            for (int j = 0; j < 4; j++) {				//表示第二个下标的排列位置
                if (i == j)										//这里的if语句都是用来去除重复的排列
                    continue;
                for (int k = 0; k < 4; k++) {			//表示第三个下标的排列位置
                    if (j == k || k == i)
                        continue;
                    for (int m = 0; m < 4; m++) {//表示第四个下标的排列位置
                        if (m == k || m == j || m == i)
                            continue;
                        int[] b = new int[] { a[i], a[j], a[k], a[m] };
                        //给出一个数组来保存这一次循环的排列再把它加到边长数组中
                        arrayList.add(b);
                    }
                }
            }
        }
        return arrayList;
    }


    /**
     * 根据所有数字组合 以及 + - * / () 所有可能
     * 所有可能结果存放List<String> 为后续遍历计算24点得到所有可能。
     * @param list combine4Num方法返回结果
     * @return 所有组合
     */
    private List<String> getAllCombine(List<int[]> list) {

        //存放所有结果
        List<String> allStr = new ArrayList<>();

        String[] strings1 = {"+", "-", "*", "/"};

        StringBuilder stringBuilder = new StringBuilder();

        for (int[] ints1 : list) {
            for (int j = 0; j < strings1.length; j++) {
                for (int k = 0; k < strings1.length; k++) {
                    for (int l = 0; l < strings1.length; l++) {
                        stringBuilder.append(ints1[0]);
                        stringBuilder.append(" ");
                        stringBuilder.append(strings1[j]);
                        stringBuilder.append(" ");
                        stringBuilder.append(ints1[1]);
                        stringBuilder.append(" ");
                        stringBuilder.append(strings1[k]);
                        stringBuilder.append(" ");
                        stringBuilder.append(ints1[2]);
                        stringBuilder.append(" ");
                        stringBuilder.append(strings1[l]);
                        stringBuilder.append(" ");
                        stringBuilder.append(ints1[3]);


                        allStr.add(stringBuilder.toString());

                        String s = stringBuilder.toString();


                        //处理括号
                        stringBuilder.delete(0, stringBuilder.length());
                        String[] s1 = s.split(" ");
                        for (int i = 0; i < 5; i++) {
                            for (int z = 0; z < s1.length; z++) {
                                if(i <= 2) {
                                    if(z == i * 2 + 2) {
                                        stringBuilder.append(s1[z]);
                                        stringBuilder.append(" ");
                                        stringBuilder.append(")");
                                        stringBuilder.append(" ");
                                    }else if(z == i * 2 ){
                                        stringBuilder.append("(");
                                        stringBuilder.append(" ");
                                        stringBuilder.append(s1[z]);
                                        stringBuilder.append(" ");
                                    }else {
                                        if(z == s1.length - 1) {
                                            stringBuilder.append(s1[z]);
                                        }else {
                                            stringBuilder.append(s1[z]);
                                            stringBuilder.append(" ");
                                        }
                                    }
                                }else {
                                    if(z == 2 * (i - 3)) {
                                        stringBuilder.append("(");
                                        stringBuilder.append(" ");
                                        stringBuilder.append(s1[z]);
                                        stringBuilder.append(" ");
                                    }else if(z == 2 * (i - 1)) {
                                        stringBuilder.append(s1[z]);
                                        stringBuilder.append(" ");
                                        stringBuilder.append(")");
                                        stringBuilder.append(" ");
                                    }else {
                                        stringBuilder.append(s1[z]);
                                        stringBuilder.append(" ");
                                    }
                                }
                            }
                            allStr.add(stringBuilder.toString());
                            stringBuilder.delete(0, stringBuilder.length());
                        }

                    }
                }
            }
        }
        return allStr;
    }

    /**
     * 返回计算结果是24的List<String>
     * @param allStr 所有可能的集合
     * @return 得到匹配的结果
     */
    private List<String> getMatch24Result(List<String> allStr) {

        List<String> matchResult = new ArrayList<>();
        for (String s : allStr) {
            int result = this.calculator.cal(s);
            if(result == 24) {
                matchResult.add(s);
            }
        }
        return matchResult;
    }

    /**
     *返回所有满足的结果
     */
    public List<String> getResult(int[] ints) {
        ArrayList<int[]> ints1 = combine4Num(ints);
        List<String> allCombine = getAllCombine(ints1);
        return getMatch24Result(allCombine);
    }


    public static void main(String[] args) {

        int[] a = {5, 6, 3, 4};

        Game24 game24 = new Game24(new Calculator());
        List<String> result = game24.getResult(a);
        for (String s : result) {
            System.out.println(s);
        }


    }



}
