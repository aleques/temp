public class Atlassian {

    public static void main(String[] args) {
        System.out.println(decode(7792875));
    }

    public static String decode (int input) {

        char[] encryptPattern =  {'0', 'a', 't', 'l', 's', 'i', 'n'};

        String base7Input = base10toBase7(input);
        String result = "";

        for (Character c : base7Input.toCharArray()) {
            result += encryptPattern[Integer.parseInt(c.toString())];
        }

        return result;
    }

    private static String base10toBase7(int input) {

        if (input < 0) {
            return null;
        } else if (input < 7) {
            return String.valueOf(input);
        } else if (input == 7) {
            return "10";
        } else {
            return base10toBase7Aux(input);
        }
    }

    private static String base10toBase7Aux(int input) {
        if (input == 0) {
            return "0";
        } else {
            int exponent = (int) (Math.log(input) / Math.log(7));
            int divisor = (int) Math.pow(7, exponent);
            return (input / divisor) + base10toBase7(input % divisor);
        }
    }
}
