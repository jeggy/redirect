@file:OptIn(ExperimentalForeignApi::class)

import kotlinx.cinterop.*
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fread

actual fun getEnvironmentVariables(): Map<String, String> {
    // On Linux, /proc/self/environ contains NUL-separated KEY=VALUE entries
    val path = "/proc/self/environ"
    val file = fopen(path, "rb") ?: return emptyMap()
    try {
        val buffer = ByteArray(4096)
        val sb = StringBuilder()
        buffer.usePinned { pinned ->
            while (true) {
                val read = fread(pinned.addressOf(0), 1.convert(), buffer.size.convert(), file).toInt()
                if (read <= 0) break
                sb.append(buffer.decodeToString(0, read))
            }
        }
        val text = sb.toString()
        val map = mutableMapOf<String, String>()
        for (entry in text.split('\u0000')) {
            if (entry.isEmpty()) continue
            val idx = entry.indexOf('=')
            if (idx > 0) {
                map[entry.substring(0, idx)] = entry.substring(idx + 1)
            }
        }
        return map
    } finally {
        fclose(file)
    }
}