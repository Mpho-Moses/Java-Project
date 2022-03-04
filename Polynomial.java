import java.util.*;
//Author: Mpho Moses
//A class with a number of methods for performing operations on a polynomial
public class Polynomial{
    private int exp;
    private double coefficient;
    private ArrayList<Double> coefficients_list;
    public Polynomial()
    {
        coefficients_list = new ArrayList<>();
        exp = 0;
        coefficient = 0;
    }
    //set the coefficient value andb add it to the list of coefficients
    public void setCoefficient(int exp, double coefficient)
    {
        this.exp = exp;
        this.coefficient = coefficient;
        coefficients_list.add(coefficient);
    }
    public double getCoefficient(int exp)
    {
        for(int i = 0; i <= coefficients_list.size()-1; i++){
            if(i==exp)
                return coefficients_list.get(i);
        }
        return 0;  //return 0 if the coefficient isn't in the list of coefficients
    }
    public void evaluate(double x_value)
    {
        double result = 0;
        for(int i = 1; i<= coefficients_list.size()-1;i++)
        {
            result+=getCoefficient(i)*Math.pow(x_value,i); //multiply each coefficient by x_value raised to exponent i and add to result
        }
        result+=getCoefficient(0); //add the constant term at position 0 to the result

        System.out.println("The value of the function "+ this.toString()+ " at " +x_value+" is "+result);
    }
    public void createPolynomial()
    {
        System.out.println("==============Please create a polynomial=================");
        System.out.println("\nThe polynomial will have the structure C0 + C1*x + C2*x^2 + C3*x^3 + ... + Cn*x^n\n");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Enter coefficient "+exp+" (press enter if done): ");
            String input = scanner.nextLine();
            if(input.equals((""))) {                     //Exit program if user pressed enter with no input
                return;
            }
            coefficient = Double.parseDouble(input);
            setCoefficient(exp, coefficient);
            exp++;
        }
    }
    //method that multiplies a polynomial by a scalar
    public void scale(double scalar)
      {
        //create a temporary arraylist of coefficients and multiply each coefficient by a scalar
	//then point the old list to the temp list
        ArrayList<Double> temp = new ArrayList<>();
        for (int j = 0; j <= coefficients_list.size() - 1; j++) {
            temp.add(scalar * coefficients_list.get(j));
        }
        coefficients_list = temp;
      }
     public Polynomial addPolynomial(Polynomial otherPolynomial)
       {

        Polynomial newPolynomial = new Polynomial(); //Take in new polynomial as a parameter and instantiate it

        double constantTerm = otherPolynomial.getCoefficient(0)+this.getCoefficient(0);
            newPolynomial.coefficients_list.add(constantTerm);                                  //add the constant term of the current polynomial to the new polynomial
                                                                                                                //assuming there is at least one term in the polynomial
        int curPolynomialSize = this.coefficients_list.size();            //where curPolynomialSize refers to the size of the calling object
        int otherPolynomialSize = otherPolynomial.coefficients_list.size();

        //Cater for cases where the number of terms in each of the polynomials may be different
        if(curPolynomialSize < otherPolynomialSize)
        {
            for (int i = 1; i <= curPolynomialSize; i++) //add like terms, end at the end of the smaller coefficient list
            {
                double sum = this.getCoefficient(i)+otherPolynomial.getCoefficient(i);
                if(sum!=0)
                    newPolynomial.coefficients_list.add(sum);
            }
            for(int j=curPolynomialSize;j < otherPolynomialSize;j++) //take the remaining coefficients in the larger list and add to the new polynomial
            {
                newPolynomial.coefficients_list.add(otherPolynomial.getCoefficient(j));
            }
        }
        else if(otherPolynomialSize < curPolynomialSize)
        {
            for(int x = 1; x <= otherPolynomialSize;x++){    //add like terms, end at the end of the smaller coefficient list
                double sum = this.getCoefficient(x)+otherPolynomial.getCoefficient(x);
                if(sum!=0)
                    newPolynomial.coefficients_list.add(this.getCoefficient(x)+otherPolynomial.getCoefficient(x));
            }
            for(int y = otherPolynomialSize + 1; y < curPolynomialSize;y++) //take the remaining coefficients in the larger list and add to the new polynomial
            {
                newPolynomial.coefficients_list.add(this.getCoefficient(y));
            }

        }
        else if(curPolynomialSize==otherPolynomialSize)
        {
            for(int o = 1; o < curPolynomialSize; o++){
                double sum = this.getCoefficient(o)+ otherPolynomial.getCoefficient(o);
                if(sum!=0)
                    newPolynomial.coefficients_list.add(sum);
            }
        }
        return newPolynomial;
    }
    public Polynomial derivative()
        {
	 
         Polynomial derivative = new Polynomial();
	 //Multiply each coefficient by the exponent i, excluding the coefficient at position 0 since it will always be a constant
        if(coefficients_list.size()==1){
            derivative.coefficients_list.add(0.0);  //return a derivative of 0 if the coefficient is constant
        }
        for(int i = 1; i <= coefficients_list.size()-1; i++)
        {
            derivative.coefficients_list.add(getCoefficient(i) * (i));
        }
	if(derivative == null) System.out.println("The derivative does not exist!");

        return derivative;
    }

    public String toString()
    {
        String result = "";
        if(coefficients_list.size()==1)              //if the polynomial has one term, return the term
            return result+coefficients_list.ge);
        if(coefficients_list.get(0)==0)
            result+="";
        else
            result+=coefficients_list.get(0);
        for(int i = 1; i < coefficients_list.size()-1; i++){
            double term=getCoefficient(i);
            if(i==1){                                       //consider the constant term and the term with power 1 separately, to avoid printing their exponents
                //term = coefficients_list.get(i);
                if(term < 0){
                    term = Math.abs(term);
                    if(coefficients_list.get(0)==0)
                        result+=term+"x";
                    else
                        result+=" - "+term+"x";
                }else if(term==0){
                    result+="";
                }
                else{
                    if(coefficients_list.get(0)==0)  //avoid printing positive sign
                        result+=term+"x";
                    else
                        result+=" + "+term+"x";
                }
            }
            else if(i==2){
                //term = coefficients_list.get(i);
                if(term < 0){
                    term = Math.abs(term);
                    result+=" - "+term+"x^"+i;
                }else if(term==0){
                    result+="";
                }
                else{
                    if(getCoefficient(i)==0)
                        result+=term+"x^"+i;
                    else
                        result += " + "+term+"x^"+i;
                }
            }
            else{
                //term = coefficients_list.get(i);
                if(term < 0){
                    term = Math.abs(term);
                    result+=" - "+term+"x^"+ i;
                }else if(term==0){
                    result+="";
                }
                else{
                    result +=" + "+term+"x^" + i;
                }
            }
        }
        if(getCoefficient(coefficients_list.size()-1)<0) {
            double term = getCoefficient(coefficients_list.size()-1);//coefficients_list.get(coefficients_list.size()-1);
            term = Math.abs(term);
            result += " - " + term + "x^" + (coefficients_list.size() - 1);
        }
        else {
            double sum = 0;
            for(int i = 0; i<=coefficients_list.size()-2;i++)
                sum+=coefficients_list.get(i);
            if(sum==0 && sum+getCoefficient(coefficients_list.size()-1)!=0)
                result+=getCoefficient(coefficients_list.size() - 1) + "x^" + (coefficients_list.size() - 1);
            else
                result+=" + "+getCoefficient(coefficients_list.size() - 1) + "x^" + (coefficients_list.size() - 1);
        }
        return result;
    }
}
