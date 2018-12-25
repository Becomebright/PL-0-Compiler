package bright.Compiler;

import java.util.ArrayList;
import java.util.List;

enum Operator{
    INT, CAL, LIT, LOD, STO, JMP, JPC, OPR, RED, WRT
}

//TODO RED和WRT？
/**
 *    代码的具体形式：
 *    FLA
 *    F段代表伪操作码
 *    L段代表调用层与说明层的层差值
 *    A段代表位移量（相对地址）
 *
 *    INT：为被调用的过程（包括主过程）在运行栈S中开辟数据区，这时A段为所需数据单元个数（包括三个连接数据）；L段恒为0。
 *    CAL：调用过程，这时A段为被调用过程的过程体（过程体之前一条指令）在目标程序区的入口地址。
 *    LIT：将常量送到运行栈S的栈顶，这时A段为常量值。
 *    LOD：将变量送到运行栈S的栈顶，这时A段为变量所在说明层中的相对位置。
 *    STO：将运行栈S的栈顶内容送入某个变量单元中，A段为变量所在说明层中的相对位置。
 *    JMP：无条件转移，这时A段为转向地址（目标程序）。
 *    JPC：条件转移，当运行栈S的栈顶的布尔值为假（0）时，则转向A段所指目标程序地址；否则顺序执行。
 *    OPR：关系或算术运算，A段指明具体运算，例如A=2代表算术运算“＋”；A＝12代表关系运算“>”等等。运算对象取自运行栈S的栈顶及次栈顶。
 *
 *    LIT 0, a	取常量a放到数据栈栈顶
 *    OPR 0, a	执行运算，a表示执行何种运算(+ - * /)
 *    LOD l, a	取变量放到数据栈栈顶(相对地址为a,层次差为l)
 *    STO l, a	将数据栈栈顶内容存入变量(相对地址为a,层次差为l)
 *    CAL l, a	调用过程(入口指令地址为a,层次差为l)
 *    INT 0, a 	数据栈栈顶指针增加a
 *    JMP 0, a 	无条件转移到指令地址a
 *    JPC 0, a 	条件转移到指令地址a
 *    RED l, a	从命令行读入一个输入置于栈顶
 *    WRT 0, 0  栈顶值输出至屏幕并换行
 *    OPR 0, 0	过程调用结束后,返回调用点并退栈
 *    OPR 0, 1	栈顶元素取反
 *    OPR 0, 2	次栈顶与栈顶相加，退两个栈元素，结果值进栈
 *    OPR 0, 3	次栈顶减去栈顶，退两个栈元素，结果值进栈
 *    OPR 0, 4	次栈顶乘以栈顶，退两个栈元素，结果值进栈
 *    OPR 0, 5	次栈顶除以栈顶，退两个栈元素，结果值进栈
 *    OPR 0, 6	栈顶元素的奇偶判断，结果值在栈顶
 *    OPR 0, 7
 *    OPR 0, 8	次栈顶与栈顶是否相等，退两个栈元素，结果值进栈
 *    OPR 0, 9	次栈顶与栈顶是否不等，退两个栈元素，结果值进栈
 *    OPR 0, 10	次栈顶是否小于栈顶，退两个栈元素，结果值进栈
 *    OPR 0, 11	次栈顶是否大于等于栈顶，退两个栈元素，结果值进栈
 *    OPR 0, 12	次栈顶是否大于栈顶，退两个栈元素，结果值进栈
 *    OPR 0, 13	次栈顶是否小于等于栈顶，退两个栈元素，结果值进栈
 */
public class PcodeList {
    private List<Pcode> pcodeList;

    PcodeList() {
        this.pcodeList = new ArrayList<>();
    }

    List<Pcode> getPcodes() {
        return pcodeList;
    }

    public int size(){
        return pcodeList.size();
    }

    void gen(Operator F, int L, int A){
        pcodeList.add(new Pcode(F, L, A));
    }
}
