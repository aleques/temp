import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test1 {

    public static void main(String[] args) throws IOException {

        System.out.println(calculateTax(180001));
        System.out.println(calculateTax(37009));
        System.out.println(calculateTax(0));
        System.out.println(calculateTax(1));
        System.out.println(calculateTax(2));
        System.out.println(calculateTax(18200f));
        System.out.println(calculateTax(18201f));
    }

    // Complete the calculateTax function below.
    static float calculateTax(float income) {

        if (income <= 0) {
            return 0;
        }

        List<TaxInfo> taxInfoList = new ArrayList<>();

        taxInfoList.add(new TaxInfo(1f, 18200f, 0f));
        taxInfoList.add(new TaxInfo(18201f, 37000f, 0.19f));
        taxInfoList.add(new TaxInfo(37001f, 87000f, 0.325f));
        taxInfoList.add(new TaxInfo(87001f, 180000f, 0.37f));
        taxInfoList.add(new TaxInfo(180001f, null, 0.45f));
        Collections.reverse(taxInfoList);

        float finalTax = 0f;

        for (TaxInfo taxInfo : taxInfoList) {

            float taxableValue = taxInfo.getTaxableValue(income);

            if (taxableValue > 0) {
                finalTax += taxInfo.getTaxPerDollar() * taxableValue;
                income -= taxableValue;
            }
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

        public float getTaxableValue(Float incomeValue){

            if (incomeValue > 0 && isIncomeInRange(incomeValue)) {
                Float taxableValue = incomeValue - minIncome + 1;
                return taxableValue;
            } else {
                return 0f;
            }

        }

        public Float getTaxPerDollar() {
            return taxPerDollar;
        }
    }


}



