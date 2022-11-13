package com.example.tele2demo.domain

abstract class Mapper<in FROM,out TO> {
    abstract fun map(from:FROM):TO
}