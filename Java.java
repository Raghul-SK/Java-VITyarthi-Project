import java.util.*;
public class Java
{
    public static void main (String args [])
    {
        if(args.length== 0)
        {
            interactiveShell();
            return;
        }
        if(args.length == 1 && (args[0].equals("list") || args[0].equals("help")))
        {
            printHelp();
            return;
        }
        String number_str=args[0];
        String from_src=args[1];
        String target="";
        if(args.length==3)
        {
            target=args[2];
        }
        else
        {
            System.out.println("Enter a valid Syntax like 50 km m");
            return;
        }
        Process(number_str, from_src, target);
    }
    static void Process (String number_, String from_, String target_)
    {
        double val;
        try
        {
            val=Double.parseDouble(number_);
        }
        catch (Exception e)
        {
            System.out.println("Error : input "+number_ +" it is not a number");
            return;
        }
        String from= normalize(from_);
        String to = normalize(target_);
        if(isTemp(from) && isTemp(to))
        {
            double res = runTemp(val, from, to);
            System.out.printf("%.2f %s = %.2f %s%n", val, from.toUpperCase(), res, to.toUpperCase());
            return;
        }
        double fFactor= getFactor(from);
        double tFactor= getFactor(to);
        if(fFactor >0 && tFactor >0 && getGroup (from).equals(getGroup(to)))
        {
            double res= (val*fFactor)/tFactor;
            System.out.printf("%.4f %s = %.4f %s%n", val, from.toUpperCase(), res, to.toUpperCase());
        }
        else
        {
            System.out.println("Can't convert between" +from_+ " and "+target_);
        }
}
        static String getGroup(String u)
        {
            if(u.matches( "mm|cm|m|km|in|ft|yd")) return "len";
            if(u.matches( "mg|g|kg|ton|oz|lb")) return "mass";
            if(u.matches( "s|min|h|day|wk")) return "time";
            if(u.matches( "mps|kph|mph|knot")) return "speed";
            if(u.matches( "b|kb|mb|gb|tb")) return "data";
            if(u.matches( "usd|eur|inr|gbp|jpy|cad|aud")) return "curr";
            else return "none";
        }
        static double getFactor(String u)
        {
            return switch (u)
            {
                case "mm" -> 0.001;
                case "cm" -> 0.01;
                case "m" -> 1.0;
                case "km" -> 1000.0;
                case "in" -> 0.0254;
                case "ft" -> 0.3048;
                case "yd" -> 0.9144;
                case "mi" -> 1609.344;

                case "mg" -> 0.000001;
                case "g" -> 0.001;
                case "kg" -> 1.0;
                case "ton" -> 1000.0;
            case "oz" -> 0.0283495;
            case "lb" -> 0.453592;

            case "s" -> 1.0;
            case "min" -> 60.0;
            case "h" -> 3600.0;
            case "day" -> 86400.0;
            case "wk" -> 604800.0;

            case "mps" -> 1.0;
            case "kph" -> 0.277778;
            case "mph" -> 0.44704;
            case "knot" -> 0.514444;

            case "b" -> 1.0;
            case "kb" -> 1024.0;
            case "mb" -> 1048576.0;
            case "gb" -> 1073741824.0;
            case "tb" -> 1099511627776.0;

            case "usd" -> 1.0;
            case "eur" -> 1.08;
            case "inr" -> 0.012;
            case "gbp" -> 1.27;
            case "jpy" -> 0.0064;
            case "cad" -> 0.73;
            case "aud" -> 0.65;

            default -> -1.0;
            };
        }
        static boolean isTemp(String s)
        {
            return s.equals("c") || s.equals("f") || s.equals("k");
        }
        static double runTemp(double v, String f, String t) {
        double c = f.equals("f") ? (v - 32) * 5 / 9 : (f.equals("k") ? v - 273.15 : v);
        if (t.equals("f")) return (c * 9 / 5) + 32;
        if (t.equals("k")) return c + 273.15;
        return c;
    }
        static String normalize(String txt) {
        String s = txt.toLowerCase().trim();
        return switch (s) {
            case "dollars", "dollar", "bucks" -> "usd";
            case "rupees", "rupee" -> "inr";
            case "euros", "euro" -> "eur";
            case "pounds", "pound" -> "gbp";
            case "yen" -> "jpy";
            case "meters", "meter" -> "m";
            case "kilometers", "kilometer" -> "km";
            case "centimeters", "centimeter" -> "cm";
            case "millimeters", "millimeter" -> "mm";
            case "miles", "mile" -> "mi";
            case "feet", "foot" -> "ft";
            case "inches", "inch" -> "in";
            case "yards", "yard" -> "yd";
            case "kilos", "kilo", "kilograms", "kilogram" -> "kg";
            case "grams", "gram" -> "g";
            case "milligrams", "milligram" -> "mg";
            case "ounces", "ounce" -> "oz";
            case "lbs" -> "lb";
            case "seconds", "second", "sec" -> "s";
            case "minutes", "minute" -> "min";
            case "hours", "hour", "hr" -> "h";
            case "days" -> "day";
            case "weeks" -> "wk";
            case "celsius" -> "c";
            case "fahrenheit" -> "f";
            case "kelvin" -> "k";
            case "kmh", "km/h" -> "kph";
            case "m/s" -> "mps";
            case "bytes", "byte" -> "b";
            case "kilobytes" -> "kb";
            case "megabytes" -> "mb";
            case "gigabytes" -> "gb";
            case "terabytes" -> "tb";
            default -> s;
        };
    }
        static void interactiveShell()
        {
            Scanner in = new Scanner(System.in);
            System.out.println("Quick Converter (enter 'quit' or 'help')");
            System.out.println("Syntax: <value> <from> <to>  (e.g., 50 km mi or 10 miles to km)\n");
            while(true)
            {
                System.out.print("> ");
                String input = in.nextLine().trim();
                if(input.equalsIgnoreCase( "quit")|| input.equalsIgnoreCase( "exit"))
                {
                    break;
                }
                if (input.equalsIgnoreCase( "help")|| input.equalsIgnoreCase( "-l"))
                {
                    printHelp();
                    continue;
                }
                if(input.isEmpty()) continue;
                String[] tok = input.split("\\s+");
                if(tok.length==3)
                {
                    Process(tok[0], tok[1], tok[2]);
                }
                else if(tok.length==4 && tok[2].equalsIgnoreCase("to"))
                {
                    Process(tok[0], tok[1], tok[3]);
                }
                else
                {
                    System.out.println("Type like: 10 miles to km");
                }
            }
            in.close();
        }
    static void printHelp()
    {
        System.out.println("Unit you can use :");
        System.out.println("  Length  : mm, cm, m, km, in, ft, yd, mi");
        System.out.println("  Mass  : mg, g, kg, ton, oz, lb");
        System.out.println("  Time  : s, min, h, day, wk");
        System.out.println("  Speed  : mps, kph, mph, knot");
        System.out.println("  Temperature  : c, f, k");
        System.out.println("  data  : b, kb, mb, gb, tb");
        System.out.println("  Currency : usd, eur, inr, gbp, jpy, cad, aud");
    }
}
