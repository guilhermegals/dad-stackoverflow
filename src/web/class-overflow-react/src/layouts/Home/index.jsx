import * as Styles from './styles'
import Header from '../../components/organisms/header'
import MiddleColumn from '../../components/organisms/middleColumn/feed'
import RightBar from '../../components/organisms/rightBar'
import LeftBar from '../../components/organisms/leftBar'
import { useEffect, useState } from 'react'
import { getPosts, createPost, onAuthenticate } from '../../infra/api'
export default function HomeLayout() {
  const [posts, setPosts] = useState([])
  useEffect(async () => {
    let token = localStorage.getItem('token')
    if (!token) {
      token = await onAuthenticate()
    }
    localStorage.setItem('token', token)

    const posts = await getPosts(token)
    setPosts(posts)
  }, [])

  return (
    <Styles.Container>
      <Header />
      <Styles.Content>
        <LeftBar />
        <MiddleColumn posts={posts} createPost={createPost} />
        <RightBar />
      </Styles.Content>
    </Styles.Container>
  )
}
