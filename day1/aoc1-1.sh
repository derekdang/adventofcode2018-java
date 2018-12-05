sum=0;
while read -r line
do
  let sum=$sum+$line 
done < input.txt
echo $sum
