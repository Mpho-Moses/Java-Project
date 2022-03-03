import java.util.*;
public class Main {
    public Main(){}
    public static void main(String[] args) {

        //Create the polynomial
        Polynomial pol = new Polynomial();
        pol.createPolynomial();

        System.out.println("\nThe given polynomial is: "+pol.toString()+"\n");
        //Test methods
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("===========Please select one of the following options(Enter only the number of the option)==========");
            System.out.println("1. Add new polynomial to the existing polynomial.");
            System.out.println("2. Evaluate polynomial at a particular value.");
            System.out.println("3. Find the derivative of the polynomial.");
            System.out.println("4. Exit");
            String input = scanner.nextLine();
            int option = Integer.parseInt(input);
            if(option==4)
            {
                System.out.println("***The program has been terminated***");
                return;
            }
            if(option==1)
            {
                Polynomial newPolynomial = new Polynomial();
                newPolynomial.createPolynomial();
                System.out.print("\nYou have entered the polynomial: "+newPolynomial.toString()+"\n");
                System.out.println("\nWould you like to multiply the polynomial by a scalar? (ENTER Y/N)");
                String choice = scanner.nextLine();
                if(choice.equalsIgnoreCase("Y")){
                    System.out.print("Please enter the number with which you want to multiply the function: ");
                    double scalar = Double.parseDouble(scanner.nextLine());
                    newPolynomial.scale(scalar);

                    System.out.print("\nThe new polynomial multiplied by "+scalar+" is "+newPolynomial.toString()+" and will now be added to "+pol.toString()+"\n");
                }
                Polynomial sum = pol.addPolynomial(newPolynomial);
                System.out.println("\nThe sum of the two polynomials is "+sum.toString());
            }else if(option == 2)
            {
                System.out.print("\nPlease enter the value at which you want to evaluate the polynomial: "");
                double value = Double.parseDouble(scanner.nextLine());
                System.out.println();
                pol.evaluate(value);
                System.out.println();
            }else if(option==3){
                System.out.println("\nThe derivative of the function is "+pol.derivative().toString());
            }
            System.out.println();
        }
    }
}
