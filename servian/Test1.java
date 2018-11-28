import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {

        NumberFormat nf = new DecimalFormat("0.00");

        System.out.println(nf.format(calculateTax(180001)));
        System.out.println(nf.format(calculateTax(37009)));
        System.out.println(nf.format(calculateTax(0)));
        System.out.println(nf.format(calculateTax(1)));
        System.out.println(nf.format(calculateTax(2)));
        System.out.println(nf.format(calculateTax(18200)));
        System.out.println(nf.format(calculateTax(18201)));
    }

    // Complete the calculateTax function below.
    static float calculateTax(float income) {

        if (income <= 0) {
            return 0;
        }

        List<TaxInfo> taxInfoList = new ArrayList<>();

        taxInfoList.add(new TaxInfo(1, 18200f, 0f));
        taxInfoList.add(new TaxInfo(18201, 37000f, 0.19f));
        taxInfoList.add(new TaxInfo(37001, 87000f, 0.325f));
        taxInfoList.add(new TaxInfo(87001, 180000f, 0.37f));
        taxInfoList.add(new TaxInfo(180001, null, 0.45f));

        float finalTax = 0;

        for (TaxInfo taxInfo : taxInfoList) {
            finalTax += taxInfo.getTax(income);
        }

        return getDecimalRoundUp(finalTax);

    }

    private static Float getDecimalRoundUp(Float value) {
        return value == 0 ? 0 : new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    static class TaxInfo {

        private float minIncome;
        private Float maxIncome;
        private Float taxPerDollar;

        public TaxInfo(float minIncome, Float maxIncome, Float taxPerDollar) {
            this.minIncome = minIncome;
            this.maxIncome = maxIncome;
            this.taxPerDollar = taxPerDollar;
        }

        public boolean isIncomeInRange(float incomeValue) {
            return incomeValue >= minIncome && (maxIncome == null || incomeValue <= maxIncome);
        }

        public float getTax(Float incomeValue) {

            float value = 0;

            if (incomeValue > 0) {
                if (isIncomeInRange(incomeValue)) {
                    value = incomeValue - minIncome + 1;
                } else if (maxIncome != null && incomeValue > maxIncome) {
                    value = maxIncome - minIncome + 1;
                }
            }
            return value * taxPerDollar;

        }
    }

}
