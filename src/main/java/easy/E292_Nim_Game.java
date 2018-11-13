package easy;

public class E292_Nim_Game {
    /**
     * 从总数为3开始分析，发现3必赢，4必输。所以，凡是可以把问题变为4的，都必输。
     * 每个人都是聪明人，所以8的时候，先走的人无论走几步，后走的人都可以把问题变为4。
     * 由此得出，凡是4的倍数的问题，先走的人都必输。
     * 不是4的倍数的问题，先走的人可以一步把问题变为4的倍数，然后每次都在另一个人走完后把问题变为4的倍数，则必赢。
     * <p>
     * 对于拿到最后一个为胜的标准来说，n%(k+1)==0的情形，先走的人必输，否则必赢。
     * 对于拿到最后一个为负的标准来说，(n+k)%(k+1)==0的情形，先走的人必输，否则必赢。
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        return n > 0 && n % 4 != 0;
    }
}