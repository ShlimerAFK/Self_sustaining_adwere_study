#include <iostream>
#include <cstdlib>
#include <fstream>

using namespace std;

void write_pid(){

    pid_t pid = getpid();

    ofstream pid_file("cpp_pid.txt");

    pid_file << pid;

    pid_file.close();

}

string get_java_pid(){

    string text;

    ofstream MyFile("filename.txt");

    ifstream file_to_read("java_pid.txt");

    while(getline (file_to_read, text)){

        cout << "Java PID: " << text;

    }

    file_to_read.close();

    return text;
    
}


void check_if_javas_running(string input_pid){

    string pid = input_pid;
    string command = "powershell.exe Get-Process -id ";

    string full_command = command + pid;
    char *cstr_command = full_command.data();

    int return_code = system( cstr_command );

    if(return_code == 0){
        cout << "Java is running" << endl;
    }
    else {
        cout << "Java is not running" << return_code << endl;
    }

}

int main(int argc, char const *argv[])
{
    cout << "Starting..." << endl;

    write_pid();
    
    check_if_javas_running( get_java_pid() );
    
    return 0;
}


