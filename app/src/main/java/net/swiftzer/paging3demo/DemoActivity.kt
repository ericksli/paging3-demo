package net.swiftzer.paging3demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.transaction
import dagger.hilt.android.AndroidEntryPoint
import net.swiftzer.paging3demo.databinding.DemoActivityBinding

private const val LIST_FRAGMENT = "list_fragment"

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {
    private lateinit var binding: DemoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoActivityBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        if (supportFragmentManager.findFragmentByTag(LIST_FRAGMENT) == null) {
            supportFragmentManager.commit {
                replace(binding.listFragment.id, ListFragment(), LIST_FRAGMENT)
            }
        }
    }
}
