#include <iostream>
#include <cstdlib>
#include <fstream>
#include <chrono>
#include <thread>

using namespace std;

void write_pid(){

    pid_t pid = getpid();

    ofstream pid_file("cpp_pid.txt");

    pid_file << pid;

    pid_file.close();

}

string get_java_pid(){

    string text;

    ifstream file_to_read("java_pid.txt");

    while(getline (file_to_read, text)){

        cout << "Java PID: " << text;

    }

    file_to_read.close();

    return text;
    
}

void start_java(){

    string powershell = "Powershell.exe ";
    string command = "start ";
    string filepath = "popup.exe";

    string full_command = powershell + command + filepath;

    char *cstr_command = full_command.data();

    int return_code = system(cstr_command);

}

void check_if_java_is_running(string input_pid){

    string pid = input_pid;
    string command = "powershell.exe Get-Process -id ";

    string full_command = command + pid;
    char *cstr_command = full_command.data();

    int return_code = system( cstr_command );

    if(return_code == 0){
        cout << "Java is running" << endl;

    }
    else {
        cout << "Java is not running: " << return_code << endl;
        
        start_java();
    }

}

void wait(int seconds){

    cout << "Timer started";

    this_thread::sleep_for(chrono::seconds(seconds));

    cout << "Timer ended";

}

int main(int argc, char const *argv[])
{
    cout << "Starting..." << endl;

    write_pid();
    
    while (true)
    {
        check_if_java_is_running( get_java_pid() );

        wait(10);

    }
    
    
    return 0;
}


