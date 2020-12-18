package androidx.fragment.app.ktx.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.ktx.getMaxState
import androidx.fragment.app.ktx.getMaxStateWithParent
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.panpf.tools4a.test.ktx.getFragmentSync
import com.github.panpf.tools4a.test.ktx.launchFragment
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentInsiderTest {

    @Test
    fun testGetMaxStateWithParent() {
        val parentFragmentScenario = ParentFragment::class.launchFragment()
        val parentFragment = parentFragmentScenario.getFragmentSync()
        val childFragment = parentFragment.childFragmentManager.fragments.single()
        val grandsonFragment = childFragment.childFragmentManager.fragments.single()

        Assert.assertEquals(Lifecycle.State.RESUMED, parentFragment.getMaxState())
        Assert.assertEquals(Lifecycle.State.RESUMED, parentFragment.getMaxStateWithParent())
        Assert.assertEquals(Lifecycle.State.RESUMED, childFragment.getMaxState())
        Assert.assertEquals(Lifecycle.State.RESUMED, childFragment.getMaxStateWithParent())
        Assert.assertEquals(Lifecycle.State.RESUMED, grandsonFragment.getMaxState())
        Assert.assertEquals(Lifecycle.State.RESUMED, grandsonFragment.getMaxStateWithParent())

        parentFragmentScenario.onFragment {
            it.requireActivity().supportFragmentManager.beginTransaction()
                    .setMaxLifecycle(it, Lifecycle.State.CREATED)
                    .commit()
        }
        Thread.sleep(200)

        Assert.assertEquals(Lifecycle.State.CREATED, parentFragment.getMaxState())
        Assert.assertEquals(Lifecycle.State.CREATED, parentFragment.getMaxStateWithParent())
        Assert.assertEquals(Lifecycle.State.RESUMED, childFragment.getMaxState())
        Assert.assertEquals(Lifecycle.State.CREATED, childFragment.getMaxStateWithParent())
        Assert.assertEquals(Lifecycle.State.RESUMED, grandsonFragment.getMaxState())
        Assert.assertEquals(Lifecycle.State.CREATED, grandsonFragment.getMaxStateWithParent())
    }

    class ParentFragment : Fragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            childFragmentManager.beginTransaction()
                    .add(ChildFragment(), "ChildFragment")
                    .commit()
        }
    }

    class ChildFragment : Fragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            childFragmentManager.beginTransaction()
                    .add(GrandsonFragment(), "GrandsonFragment")
                    .commit()
        }
    }

    class GrandsonFragment : Fragment()
}