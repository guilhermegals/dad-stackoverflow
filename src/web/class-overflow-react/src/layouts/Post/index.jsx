import * as Styles from './styles'
import Header from '../../components/organisms/header'
import MiddleColumn from '../../components/organisms/middleColumn/post'
import RightBar from '../../components/organisms/rightBar'
import LeftBar from '../../components/organisms/leftBar'
import { useEffect, useState } from 'react'
import { getPostsByID, sendComment, sendLike } from '../../infra/api'
export default function PostLayout() {
  const [post, setPost] = useState({})

  useEffect(async () => {
    const token = localStorage.getItem('token')
    const postID = window.location.pathname.split('/')[2]
    const post = await getPostsByID({ postID, token })
    setPost(post)
  }, [])
  return (
    <Styles.Container>
      <Header />
      <Styles.Content>
        <LeftBar />
        <MiddleColumn post={post} sendComment={sendComment} sendLike={sendLike} />
        <RightBar />
      </Styles.Content>
    </Styles.Container>
  )
}
