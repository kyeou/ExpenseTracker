//
// Created by kyeou on 08/April/22.
//

// the whole JNI scheme will work to create the json values and returns strings back to java to read them into the files

#ifndef EXPENSE_TRACKER_COMPONENTS_H
#define EXPENSE_TRACKER_COMPONENTS_H

#endif // EXPENSE_TRACKER_COMPONENTS_H

#include "iostream"
#include "sstream"
#include "fstream"
#include "string"
#include "stdio.h"
#include "stdlib.h"
#include "json.hpp"

using json = nlohmann::json;

std::string months[] = {"January", "February", "March", "April", "May", "June",
                        "August", "September", "October", "November", "December"};

namespace GLOBAL_VARS
{
    json TRANSACTIONS_JSON, USER_JSON;
    int A_O_T, A_O_B;

}

using namespace GLOBAL_VARS;

class Date

{
public:
    Date()
    {
        this->month = 0;
        this->day = 0;
        this->year = 0;
    }

    Date(int month, int day, int year)
    {
        this->month = month;
        this->day = day;
        this->year = year;
    }

    std::string getMonthString(int i) { return months[i - 1]; }

    void setDate(int month, int day, int year)
    {
        this->month = month;
        this->day = day;
        this->year = year;
    }

    int getMonth() { return this->month; }
    int getDay() { return this->day; }
    int getYear() { return this->year; }

    Date getDate() { return *(this); }

    std::string getDateString()
    {
        // printf("Print Date Test: %s, %d, %d", months[date.getMonth()].substr(0, 3), date.getDay(),date.getYear());
        std::ostringstream os;
        // os << "Print Date Test: ";
        os << months[this->getMonth() - 1].substr(0, 3);
        os << " ";
        os << this->getDay();
        os << ", ";
        os << this->getYear();
        return os.str();
    }

    ~Date() {}

private:
    int month, day, year;
    std::string dateString;
};
// end of DATE

class TRANS_HANDLE
{
public:
    TRANS_HANDLE(std::string name, Date *date, float amount)
    {
        this->name = name;
        this->date = date;
        this->amount = amount;
        this->recorded = false;
    }

    std::string getTransString()
    {
        std::ostringstream os;
        os << "Name: ";
        os << name;
        os << " | Date: ";
        os << this->date->getDateString();
        os << " | Amount: ";
        os << amount;

        return os.str();
    }

    void setAmount(float amount) { this->amount = amount; }

    void setName(std::string name) { this->name = name; }

    void setDate(Date *date) { this->date = date; }

    void setRecord(bool q) { this->recorded = q; }

    // void setCorD(std::string s) { this->creditORdebit = (s.compare("Debit")) ? 0 : 1; }

    Date getDate() { return date->getDate(); }

    bool getCorD() { return this->creditORdebit; }

    std::string getName() { return this->name; }

    float getAmount() { return this->amount; }

    // parm = text read by java from transactions.json
    // returns a the json variable dumped
    std::string addTrans(std::string t)
    {
        TRANSACTIONS_JSON = json::parse(t);
        A_O_T = TRANSACTIONS_JSON.size();
        TRANSACTIONS_JSON[A_O_T]["Name: "] = this->name;
        TRANSACTIONS_JSON[A_O_T]["Date: "] = this->date->getDateString();
        TRANSACTIONS_JSON[A_O_T]["Amount: "] = this->amount;
        TRANSACTIONS_JSON[A_O_T]["ATTRIBUTE->RECORDED_BOOL: "] = this->recorded;
        return TRANSACTIONS_JSON.dump();
    }

    ~TRANS_HANDLE() {}

private:
    std::string name;
    Date *date;
    float amount;
    bool recorded;
    // 1 for credit, 0 for debit
    bool creditORdebit;
};

class USER_HANDLE
{
public:
    // java should open the user.json regardless, ift its empty it should return ""
    USER_HANDLE(std::string CON_PARM)
    {
        if (CON_PARM.compare("") == 0)
        {
            USER_JSON = {{"Name", "FirstName LastName"}, {"A_O_T", 0}, {"Budgets", {}}, {"Income", 0}, {"Scores", {}}, {"SumDebits", 0}};
        }
        else
        {
            USER_JSON = json::parse(CON_PARM);
        }
    } // end of constructor



 void addBudget(float amount)
    {
        int currAMT = USER_JSON["Budgets"].size();
    
       USER_JSON["Budgets"][currAMT] = amount;
    }


std::string recordDebits(std::string trans_parm, std::string user_parm)
    { 
        float temp = 0.0;
        int c = 0;
        TRANSACTIONS_JSON = json::parse(trans_parm);
        USER_JSON = json::parse(user_parm);

        for (json::iterator it = TRANSACTIONS_JSON.begin(); it != TRANSACTIONS_JSON.end(); ++it)
        {
            if ((*it)["ATTRIBUTE->RECORDED_BOOL: "] == false)
            {
                // std::cout << (*it)["Amount: "] << std::endl;
                float as = (*it)["Amount: "];
                temp += as;
                (*it)["ATTRIBUTE->RECORDED_BOOL: "] = true;
                // itCount++;
            } // end if
        }//end for


        USER_JSON["SumDebits"].clear();
        USER_JSON["SumDebits"] = temp;
        
        
         A_O_B =  USER_JSON["Budgets"].size();
    
     for (int i = 0; i < A_O_B; i++)
        {
            float currBud = USER_JSON["Budgets"][i];
            USER_JSON["Scores"][i] = (((currBud - temp) / currBud) * 10 < 0) ? 0 : ((currBud - temp) / currBud) * 10;
        }

    
    return T
    
    }//end recordDebits

   

    std::string USERDUMP() {
        return USER_JSON.dump();
    }

    ~USER_HANDLE() {}

private:
}; // end of class
