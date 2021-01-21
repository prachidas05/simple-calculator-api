# simple-calculator-api
# Getting Started
Launch the application from the SimpleCalculatorApplication.java

This application performs basic arithmetic operation add, subtract, multiply,divide

##Rest end point
Rest end poinst have been implemented and can be found as:

##Input
1) http://localhost:8080/simplecalculatorapi/calculator?firstNum=10&secondNum=5&operation=add
2) http://localhost:8080/simplecalculatorapi/calculator/history

## Output
1)
{
    "result": "15",
    "history": [
        "10.0+5.0=15.0"
    ]
}

2)
{
    "history": [
        "3.0+8.0=11.0"
    ]
}


