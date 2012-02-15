//
//  Author: Gabriel Smith (ges7506@rit.edu)
//  PLC Project #2
//  10-16-2010
//
open System


//////////////////////////////////////////////////
// Problem 1: Greatest Common Denominator
//////////////////////////////////////////////////
let rec gcd (a:int) (b:int) =
    if b = 0 then a
    else gcd b (a % b)

//Console.WriteLine(gcd 25 30) // 5
//Console.WriteLine(gcd 3 9) // 3


//////////////////////////////////////////////////
// Problem 2: Convert Date
//////////////////////////////////////////////////
let convertDate (date:string) = 
    let splitDate = date.Split[|'/'|]
    let month = splitDate.[0]
    let day = splitDate.[1]
    let year = splitDate.[2]

    let monthString = match month with
                        | "01" -> "January"
                        | "02" -> "February"
                        | "03" -> "March"
                        | "04" -> "April"
                        | "05" -> "May"
                        | "06" -> "June"
                        | "07" -> "July"
                        | "08" -> "August"
                        | "09" -> "September"
                        | "10" -> "October"
                        | "11" -> "November"
                        | "12" -> "December"
                        | ____ -> "Error"

    let dtime = new DateTime((int)year, (int)month, (int)day)
    (string)dtime.DayOfWeek + ", " + monthString + " " + day + ", " + year

//Console.WriteLine(convertDate "01/02/2010") //Saturday, January 02, 2010
//Console.WriteLine(convertDate "02/25/1950") //Saturday, February 25, 1950


//////////////////////////////////////////////////
// Problem 3: Count Vowels
//////////////////////////////////////////////////
type vowelTotal = { a:int; e:int; i:int; o:int; u:int }
let doCounting totals char =
    match char with
    | 'a' -> { totals with a = totals.a + 1 }
    | 'e' -> { totals with e = totals.e + 1 }
    | 'i' -> { totals with i = totals.i + 1 }
    | 'o' -> { totals with o = totals.o + 1 }
    | 'u' -> { totals with u = totals.u + 1 }
    | _   -> totals

let countVowels (input:string) = 
    let chars = Seq.toList input
    let totals = List.fold doCounting { a=0; e=0; i=0; o=0; u=0 } chars
    // print results in readable form
    Console.Write("( "+ totals.a.ToString())
    Console.Write(", "+ totals.e.ToString())
    Console.Write(", "+ totals.i.ToString())
    Console.Write(", "+ totals.o.ToString())
    Console.Write(", "+ totals.u.ToString())
    Console.WriteLine(" )")
    [|totals.a, totals.e, totals.i, totals.o, totals.u|]

//Console.WriteLine(countVowels "The quick brown fox jumps over the lazy dog") //(1, 3, 1, 4, 2)


//////////////////////////////////////////////////
// Problem 4: Transpose Matrix
//////////////////////////////////////////////////

let rec transpose matrix = 
    match matrix with
    | firstRow :: otherRows -> //make sure rows are non-empty
        match firstRow with
        | firstCol :: otherCols -> //make sure columns are non-empty
            let firstElements = List.map List.head matrix //pull first elements and map them
            let otherElements = transpose (List.map List.tail matrix) //get remaining elements and recursively transpose them
            firstElements :: otherElements //join head and tail together
        | _ -> []
    | _ -> []

//Console.WriteLine( transpose [[1; 2]; [3; 4]] ) //[[1; 3]; [2; 4]]