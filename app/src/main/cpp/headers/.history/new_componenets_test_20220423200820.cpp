#include "components.hpp"
#define randK(x)                     \
    1 + (rand() % x)


std::string ga = "[]";

int main()
{
  srand((unsigned int)time(NULL));
  std::string name = "TEST NAME";
  int month = 1, day = 30, year = 2002;
  float amount = 3.23;
  //  std::string ret = (new TRANS_HANDLE(name, new Date(month, day, year), amount))->addTrans("[]");
  // std::cout << ret << std::endl;

  USER_HANDLE u("", "");
  for (int i = 0; i < 10;i++) {
  ga = (new TRANS_HANDLE("BLEH", new Date(month, day, year), randK(100)))->addTrans(ga);
  }

 
   
establishRanks();

std::ofstream transFileWrite("test_trans.json");
transFileWrite << std::setw(5) << u.getTRANS();
transFileWrite.close();

std::string a = 
//std::cout << u.TRANSDUMP() << std::endl;
  /*
  u.addBudget(23.3);
  std::cout << u.USERDUMP() << std::endl
            << u.TRANSDUMP() << std::endl;
  std::cout << u.getUSER_FIELD("Budget") << std::endl;
  std::cout << u.getUSER_FIELD("Score") << std::endl;

  u.addBudget(56.45);
  std::cout << u.USERDUMP() << std::endl
            << u.TRANSDUMP() << std::endl;
  std::cout << u.getUSER_FIELD("Budget") << std::endl;
  std::cout << u.getUSER_FIELD("Score") << std::endl;

  u.addBudget(76.3);
  std::cout << u.USERDUMP() << std::endl
            << u.TRANSDUMP() << std::endl;
  std::cout << u.getUSER_FIELD("Budget") << std::endl;
  std::cout << u.getUSER_FIELD("Score") << std::endl;

  u.addBudget(93.3);
  std::cout << u.USERDUMP() << std::endl
            << u.TRANSDUMP() << std::endl;
  std::cout << u.getUSER_FIELD("Budget") << std::endl;
  std::cout << u.getUSER_FIELD("Score") << std::endl;

  std::string us = "Name", ba = "Balance", bu = "Budget", s = "Score";
  std::string user_name = u.getUSER_FIELD(us);
  std::string balance = u.getUSER_FIELD(ba);
  std::string budget = u.getUSER_FIELD(bu);
  std::string score = u.getUSER_FIELD(s);
  std::cout << user_name << "\t" << balance << "\t" << budget << "\t" << score << std::endl;
*/
}