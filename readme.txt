Readme

Task 2:
Run Instructions:
1. Change directory to bin
2. Execute the program by giving the following command:
java Task2 <Path_to_Input>

Design:
Main methods:
CalculateProbability:
	Calculate the probability of a given variable.
CalculateConditional:
	Calculate the Conditional probability.

Task 3:
Run Instructions:
1. Change directory to bin
2. Execute the program by giving the following command:
java bnet <Probability_Expression_to_Calculate>

The program accepts up to 6 arguments including 'given' keyword

Design and Algorithm:
1. The Bayesian network is modelled as nodes of graph.
2. Each Node has its parent and children.
3. To calculate a probability, we would need all the joint probabilities and sum them up.
EG: THere are 5 variables in our model. B, E, A, J, M
Suppose we want to compute P(B,E,A)= 	P(B,E,A,J,M)+
				     	P(B,E,A,~J,M)+
					P(B,E,A,J,~M)+
					P(B,E,A,~J,~M)

For conditional Probability: P(A/B)=P(A,B)/P(B)

By using the above formula and computing joint probabilities accordingly, You get the answer that you see in the console.