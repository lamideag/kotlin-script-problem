# Problem with JSR233 kotlin scripts

The test program is [ScriptExample.kt](src/main/kotlin/ScriptExample.kt)
It evals a script, which in turn evals a second script. 

Everything works fine in kotlin 1.4.10, but I am seeing problems with 1.4.20.

If you run the main() in ScriptExample.kt with 1.4.10, you should see the correct output:
````
I am in script1
I am in script2
script2 was completed.
````

If you change the version of kotlin to 1.4.20 and run the program, you should see this output:
```
I am in script1
I am in script1
I am in script1
I am in script1
I am in script1
I am in script1
...
```
(You will also see warnings about illegal reflective access with 1.4.20, but that is a different issue.)

As you can see, the 2nd script is never executed and the program enters an infinite loop.

This can be remedied with:  
1) Rename `src/main/resources/META-INF/services/javax.script.ScriptEngineFactory` to
`javax.script.ScriptEngineFactory2`  
2) Uncomment `implementation "org.jetbrains.kotlin:kotlin-scripting-jsr223"` in `build.gradle`
3) Reload the gradle changes

Run the program, you should see the correct output:
```
I am in script1
I am in script2
script2 was completed.
```

