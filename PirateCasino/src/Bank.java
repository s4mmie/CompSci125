
public class Bank 
{
	String user;
	int balance;
	int totalWinnings;
	int totalLosings;
	
	public void deposit(int depo)
	{
		this.balance += depo;
	}
	
	public int withdraw(int with)
	{
		if(this.balance >= with)
		{
			this.balance -= with;
			return with;
		}
		else
		{
			return 0;
		}
	}
	
	public Bank(String user, int balance, int totalWinnings, int totalLosings)
	{
		this.user = user;
		this.balance = balance;
		this.totalWinnings = totalWinnings;
		this.totalLosings = totalLosings;
	}
}
