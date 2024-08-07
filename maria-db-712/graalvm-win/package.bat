@echo off
call graalvm-win\graalvm-package.bat
call graalvm-win\graalvm-package-server.bat
call graalvm-win\graalvm-image-console.bat
call graalvm-win\graalvm-image-not-console.bat
call graalvm-win\graalvm-image-win.bat