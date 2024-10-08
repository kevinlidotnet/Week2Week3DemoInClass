SELECT sum(count) as cases, age_group FROM foodmenu2023.covidcase 
group by age_group
order by 2 

SELECT sum(count) as cases,gender FROM foodmenu2023.covidcase 
group by gender
order by 2 

SELECT sum(count) as cases,  
  EXTRACT(YEAR_MONTH  FROM date) AS month  FROM foodmenu2023.covidcase 
group by month
order by 2 


