package com.github.ebrahimi2723.onlineshop.services.site

import com.github.ebrahimi2723.onlineshop.models.site.Slider
import com.github.ebrahimi2723.onlineshop.repositories.sites.SliderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SliderService {

    @Autowired
   private lateinit var repository: SliderRepository

    //CRUD
    //insert slider
    fun insert(data: Slider): Slider {
        return repository.save(data)
    }

    //update slider
    fun update(data: Slider): Slider? {

        val oldData = getById(data.id) ?: return null      ///// if oldData is null >>>> return

        oldData.image = data.image
        oldData.link = data.link
        oldData.title = data.title
        oldData.subTitle = data.subTitle

        return repository.save(oldData)


    }


    fun getById(id: Long): Slider? {

        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    // in normally findAll return a mutableList,
    //But we can override this method in SliderRepository
    // that return a list<Slider>

    fun getAll(): List<Slider> {
        return repository.findAll()
    }


    fun delete(slider: Slider): Boolean {

        repository.delete(slider)
        return true

    }

    fun getAllCount(): Long {
        return repository.count()
    }

}