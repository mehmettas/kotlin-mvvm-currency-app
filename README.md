[![LinkedIn][linkedin-shield]][linkedin-url]

<br />
<p align="center">
  <a href="https://www.linkedin.com/in/mehmettass">
    <img src="images/cent.png" alt="" width="120" height="120">
  </a>

  <h3 align="center">Android MVVM Currency App with latest Arhitecture Compenents</h3>

  <p align="center">
    An application where I have implemented the technologies that I learned to practise more and more!
    <br />
    <a href="play link">Download the Project</a>
    .
</p>

## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [License](#license)
* [Contact](#contact)

## About The Project

[![Product Name Screen Shot][product-screenshot]]()

Cent, gives you the ability to view all the currency rates by daily datas based on specific currency. When you first open up the application, you'll see all world currency rates against one currency. You could change this base currency on the top right base button. You could easily go to the detail of the selected currency and have the detailed data with line chart in the intervals of: last one week, one month, one year and five year.

While developing this project, I have used [Foreign exchange rates API](https://exchangeratesapi.io/) where I can retrieve the latest rates data based on the parameters such like; time interval, against to a base currency, with symbols(currency) and the data. The manipulation of the data made by me. I did some calculations that the API doesn't provide. 

Kotlin - Mvvm : get conformtable
Kotlin Coroutines for asyncgronus programming
Retrofit for Htttp request
Gson
LiveData is used to communicate information back from viewmodel - Android Architecture
Koin - used to add dependencies to our classes


### Built With

While developing this project, I focused on using all the benefits of Kotlin and appliying it by following the software architecturel pattern:  MVVM. I tried to decrease the dependencies between my classes by appliying Kotlin-Koin and for asynchronous programming I used Kotlin-Coroutines. After all dealing with all this data, I used one of the android architecture compenent "LiveData" to make my application lifecycle aware. 

* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Koin](https://github.com/InsertKoinIO/koin)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [Gson - Retrofit]

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.

<!-- CONTACT -->
## Contact

Mehmet Taş - [@your_twitter](https://twitter.com/tasmehmet_) - m_tas@outlook.com

Project Link: [https://github.com/mehmettas/kotlin-mvvm-currency-app](https://github.com/mehmettas/kotlin-mvvm-currency-app)


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=flat-square
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=flat-square
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=flat-square
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=flat-square
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=flat-square
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/mehmettass/
[product-screenshot]: images/screens.png
