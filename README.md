<a id="readme-top"></a>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="[https://github.com/othneildrew/Best-README-Template](https://github.com/eltonkola/ComfyFlux)">
    <img src="app/src/main/res/mipmap-hdpi/ic_launcher_round.webp" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">ComfyFlux</h3>

  <p align="center">
    A simple android client for ComfyUi and Flux workflows!
    <br />
    <a href="https://comfyanonymous.github.io/ComfyUI_examples/flux/"><strong>Make sure to have ConfyUi installed and Flux workflows working»</strong></a>
    <br />
    <br />
    <a href="https://github.com/eltonkola/ComfyFlux/releases">Download the apk</a>
    ·
    <a href="https://github.com/eltonkola/ComfyFlux/issues/new?labels=bug&template=bug-report---.md">Report Bug</a>
    ·
    <a href="https://github.com/eltonkola/ComfyFlux/issues/new?labels=enhancement&template=feature-request---.md">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://github.com/eltonkola/ComfyFlux)

As many of us, i have multiple computers in my house, and unfortunatly the beast that can run ComfyUi is not very poratble. I have been using the web ui from a x1 carbon around the house, but there there are times i could just run it from my phone. This is why on my time off i decided to make this small app.

Here's why:
* Small, quick and easy to use
* Local first, there is only optional remote call to make yout prompt better
* For fun :smile:

This app is only for the comfyUi and flux enthusiasts, but there is no reason you cant fork it and adapt it to your needs!

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Built With

This is an android app, made with compose ui, and ktor as a client.


* [![Android][android-shield]][android-url]
* [![Jetpack Compose][compose-shield]][compose-url]
* [![Ktor][ktor-shield]][ktor-url]
* [![Coil][coil-shield]][coil-url]
* [![Kotlin][kotlin-shield]][kotlin-url]
    
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

1. First make sure you have ComfyUi running in your computer, and that your phone and computer are in the same network. Run comfy ui with the listen parameter, that will expose the server to the local network.
```
python main.py --listen
```
2. Make sure you have Flux workflows running on your comuter, and generate images from the weui ui.
[Documentation](https://comfyanonymous.github.io/ComfyUI_examples/flux/)
3. Install the app, and enter your ip and port number for your ComfyUi instance.
[Releases page] (https://github.com/eltonkola/ComfyFlux/releases)

## Usage

If you followed the steps above you should be able to connect to your ComfyUi server and use the app. Hopefully the app should be intuitive and easy to use.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- [x] MPV app
- [x] History
- [x] Queue
- [x] Promt db
- [x] Grow prompt enricher
- [ ] Better progress indicator
- [ ] Better workflow managment
- [ ] Better image gallery


See the [open issues](https://github.com/eltonkola/ComfyFlux/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

That said probbaly i will forget about this app and never spend any time on it.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the Apache License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Elton Kola - my first name and last name @gmail.com

Project Link: [https://github.com/eltonkola/ComfyFlux](https://github.com/eltonkola/ComfyFlux)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Here are some great links very yseful to this project!

* [ComfyUi](https://github.com/comfyanonymous/ComfyUI)
* [Prompts](https://huggingface.co/datasets/Gustavosta/Stable-Diffusion-Prompts)
  
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/eltonkola
[product-screenshot]: screenshots/Screenshot_20240808_184838.png

[coil-shield]: https://img.shields.io/badge/Coil-000000.svg?style=for-the-badge&logo=Coil&logoColor=white
[coil-url]: https://github.com/coil-kt/coil

[android-shield]: https://img.shields.io/badge/Android-34A853.svg?style=for-the-badge&logo=Android&logoColor=white
[android-url]: https://developer.android.com

[compose-shield]: https://img.shields.io/badge/Jetpack%20Compose-4285F4.svg?style=for-the-badge&logo=Jetpack-Compose&logoColor=white
[compose-url]: https://developer.android.com/compose

[ktor-shield]: https://img.shields.io/badge/Ktor-087CFA.svg?style=for-the-badge&logo=Ktor&logoColor=white
[ktor-url]: https://ktor.io/

[kotlin-shield]: https://img.shields.io/badge/Kotlin-7F52FF.svg?style=for-the-badge&logo=Kotlin&logoColor=white
[kotlin-url]: https://kotlinlang.org/








