#include <jni.h>

#ifdef _WIN32
#include <windows.h>
#elif __linux__
#include <unistd.h>
#endif

extern "C"
JNIEXPORT jboolean JNICALL Java_com_jpm_commons_AdminCheck_hasPrivileges(JNIEnv *env, jclass clazz) {
    int resValue;

#ifdef _WIN32
    HANDLE hToken = nullptr;
    TOKEN_ELEVATION elevation;
    DWORD dwSize;

    if (!OpenProcessToken(GetCurrentProcess(), TOKEN_QUERY, &hToken))
        return JNI_FALSE;

    if (!GetTokenInformation(hToken, TokenElevation, &elevation, sizeof(elevation), &dwSize))
        return JNI_FALSE;

    if (elevation.TokenIsElevated)
        resValue = 1;

    if (hToken != nullptr) {
        CloseHandle(hToken);
        hToken = nullptr;
    }
#elif __linux__
    resValue = (getuid() == 0 && getgid() == 0);
#endif

    return resValue;
}